package com.project_spring.Admin.controllers.Payment;

import com.project_spring.Admin.Model.Booking;
import com.project_spring.Admin.Model.Payment;
import com.project_spring.Admin.Model.Room;
import com.project_spring.Admin.Model.RoomType;
import com.project_spring.Admin.Service.Booking.BookingService;
import com.project_spring.Admin.Service.Payment.PaymentService;
import com.project_spring.Admin.Service.Room.RoomService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.ss.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    BookingService bookingService;

    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/danh-sach-hoa-don-phong", method = RequestMethod.GET)
    public String listPayment(HttpServletRequest httpServletRequest) {
        List<Payment> payments = paymentService.listAll();
        httpServletRequest.setAttribute("payments", payments);
        return "Admin/Room/list-bill";
    }

    @RequestMapping(value = "/thanh-toan-phong/bookingId={bookingId}", method = RequestMethod.GET)
    public String addPayment(HttpServletRequest httpServletRequest, @PathVariable(name = "bookingId") int bookingId) {
        Booking booking = bookingService.findBookingById(bookingId);
        Payment payment = new Payment();
        payment.setBooking(booking);
        httpServletRequest.setAttribute("payment", payment);
        return "Admin/Room/payment";
    }

    @RequestMapping(value = "/thanh-toan-phong", method = RequestMethod.POST)
    public String addPayment(HttpServletRequest httpServletRequest, @ModelAttribute("payment") Payment payment) {
        boolean result = paymentService.insert(payment);
        Room room = payment.getBooking().getRoom();
        roomService.upadateStatus(room, "Trống");
        return result ? "redirect:/danh-sach-hoa-don-phong" : "Admin/Room/payment";
    }


     @RequestMapping(value = "/xuat-file-excel/id={paymentId}", method = RequestMethod.GET)
     public String exportExcel(HttpServletRequest httpServletRequest, @PathVariable(name = "paymentId") int paymentId) {
         Payment payment = paymentService.findByPaymenId(paymentId);
         String roomName = payment.getBooking().getRoom().getRoomName();
         Date date = payment.getTransactionDate();
         XSSFWorkbook workbook = new XSSFWorkbook();
         // dat ten sheet
         XSSFSheet sheet = workbook.createSheet("Bill-" + roomName + "-" + date);

         XSSFRow row = null;
         Cell cell = null;

         // viet cac tieu de
         row = sheet.createRow(0);
         cell = row.createCell(0, CellType.STRING);
         cell.setCellValue("Mã hóa đơn");

         cell = row.createCell(1, CellType.STRING);
         cell.setCellValue("Khách hàng");

         cell = row.createCell(2, CellType.STRING);
         cell.setCellValue("Phòng");

         cell = row.createCell(3, CellType.STRING);
         cell.setCellValue("Tền phòng");

         cell = row.createCell(4, CellType.STRING);
         cell.setCellValue("Tiền khách trả");

         cell = row.createCell(5, CellType.STRING);
         cell.setCellValue("Còn nợ");

         cell = row.createCell(6, CellType.STRING);
         cell.setCellValue("Ngày lập hóa đơn");

         cell = row.createCell(7, CellType.STRING);
         cell.setCellValue("Trạng thái");


         // viet cac gia tri
         row = sheet.createRow(1);

         cell = row.createCell(0, CellType.STRING);
         cell.setCellValue(paymentId);

         cell = row.createCell(1, CellType.STRING);
         cell.setCellValue(payment.getBooking().getCustomer().getName());

         cell = row.createCell(2, CellType.STRING);
         cell.setCellValue(payment.getBooking().getRoom().getRoomName());

         cell = row.createCell(3, CellType.STRING);
         cell.setCellValue(payment.getBooking().getTotalAmount());

         cell = row.createCell(4, CellType.STRING);
         cell.setCellValue(payment.getTransactionAmount());

         cell = row.createCell(5, CellType.STRING);
         cell.setCellValue(payment.getRefund());

//         // Định dạng ô thành ngày tháng
//         CellStyle cellStyle = workbook.createCellStyle();
//         CreationHelper creationHelper = workbook.getCreationHelper();
//         cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
//         cell.setCellStyle(cellStyle);
//
//         // Định dạng ngày tháng từ chuỗi
//         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
         row.createCell(6).setCellValue(payment.getTransactionDate().toString());

         cell = row.createCell(7, CellType.STRING);
         cell.setCellValue(payment.getTransactionAmount() >= payment.getBooking().getTotalAmount() ? "Đã thanh toán" : "Còn nợ");

         // luu file
         File file = new File("Bill-" + roomName + "-" + date + ".xlsx");
         if(!file.exists()) {
             try {
                 file.createNewFile();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
         }

         FileOutputStream fileOutputStream = null;
         try {
             fileOutputStream = new FileOutputStream(file);
             workbook.write(fileOutputStream);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         } finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    System.out.println("THANH CONG");
                    System.out.println(file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
         }

         return "redirect:/danh-sach-hoa-don-phong";
   }
}