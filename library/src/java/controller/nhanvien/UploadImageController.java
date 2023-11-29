/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.nhanvien;

import entity.TaiLieu;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class UploadImageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("ThemMoiTaiLieu.jsp").forward(request, response);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Lấy phần của request chứa file
        System.out.println(getSubmittedFileName(filePart));
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String fileName = timeStamp + "_" + getSubmittedFileName(filePart);

        InputStream fileContent = filePart.getInputStream(); // Lấy nội dung file

        // Đường dẫn lưu trữ file trên server (thay đổi tùy theo nơi bạn muốn lưu trữ)
        String uploadPath = getServletContext().getRealPath("") + "/commons/images/";

//        System.out.println(uploadPath);
//         Tạo thư mục lưu trữ nếu chưa tồn tại
        Files.createDirectories(Path.of(uploadPath));

        // Tạo đường dẫn lưu trữ trên server
        Path filePath = Paths.get(uploadPath, fileName);
        System.out.println(fileName);

        // Lưu file lên server
        Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);

        String pathReturn = "images/" + fileName;
        TaiLieu taiLieu = (TaiLieu) request.getSession().getAttribute("taiLieu");
        if (taiLieu == null) {
            taiLieu = new TaiLieu();
        }
        taiLieu.setAnhBia(pathReturn);
        request.getSession().setAttribute("taiLieu", taiLieu);
        String from = request.getParameter("from");
        response.sendRedirect(from);
//        request.getRequestDispatcher(from).forward(request, response);
    }

    // Hàm để lấy tên file từ phần request
    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
