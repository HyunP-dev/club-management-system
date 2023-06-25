package kr.ac.hallym.clubmanagementsystem.controller.activity

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.ac.hallym.clubmanagementsystem.model.Executive

@WebServlet(name = "deleteActivityServlet", value = ["/delete-activity"])
class DeleteActivityServlet : HttpServlet() {
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val aid = Integer.parseInt(req.getParameter("aid"))
        val executive = req.session.getAttribute("executive") as Executive
        executive.removeActivity(aid)
        resp.sendRedirect("/")
    }
}