package com.example.supperwebapp;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

//     khởi tạo Servlet
//     tạo ra 1 cặp đối tượng duy nhất là: Request và Response. Các Request sau đó tới Servlet
//     không cần phải khởi tạo lại cặp Request và Response này nữa vì chúng chỉ cần tạo 1 lần đầu tiên
//     duy nhất. Điều này là do Servlet Container quản lý vòng đời của Servlet.
    // Vai trò của Servlet Container

    /*
    1. LifeCycle Management: quản lý vòng đời của servlet,khởi tạo,load class, call method...giải phóng
    2. JSP support: quản lý jsp
    3.Communication support: Quản lý giao tiếp giữa servlet và webserver
    **/

    // chỉ được gọi 1 lần trước khi servlet xử lý các request(trước khi service() được gọi)
    public void init() {
        message = "Hello World!";
    }

    //  service() được gọi bỏi container khi client make request, có thể được gọi nhiều lần để xử lý các request
//  service() dựa vào HTTP request để gọi các doXXX() (doPost(), doGet(),...) tương ứng.Method này được xử lý theo từng luồng riêng biệt
//   Thread riêng biệt.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    //  doPost(): được gọi bởi service tùy thuộc vào HTTP request(method= POST)
    //
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //  doGet(): được gọi bởi service tùy thuộc vào HTTP request(method= GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    // getAllProduct(): được gọi bởi service()
    public void getAllProduct(){

    }

    // chỉ được gọi chính xác 1 lần bởi container
    public void destroy() {
        // Hủy Servlet
        System.out.println("Servlet bị hủy.");
    }
}