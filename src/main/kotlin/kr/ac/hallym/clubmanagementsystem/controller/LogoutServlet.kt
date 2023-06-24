package kr.ac.hallym.clubmanagementsystem.controller

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "logoutServlet", value = ["/logout"])
class LogoutServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.session.invalidate()
        resp.sendRedirect("/index.jsp")
    }
}