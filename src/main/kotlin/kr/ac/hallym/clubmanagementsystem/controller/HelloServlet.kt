package kr.ac.hallym.clubmanagementsystem.controller

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kr.ac.hallym.clubmanagementsystem.database.Database
import kr.ac.hallym.clubmanagementsystem.repository.ClubRepository

@WebServlet(name = "helloServlet", value = ["/hello-servlet"])
class HelloServlet : HttpServlet() {
    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html"

        val out = response.writer
        out.println("<html><body>")
        out.println("${ClubRepository().findAll()}")
        out.println("</body></html>")
    }
}