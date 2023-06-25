package kr.ac.hallym.clubmanagementsystem.controller.activity

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.ac.hallym.clubmanagementsystem.model.Executive
import kr.ac.hallym.clubmanagementsystem.repository.ActivityRepository
import java.time.LocalDateTime

@WebServlet(name = "modifyActivityServlet", value = ["/modify-activity"])
class ModifyActivityServlet : HttpServlet() {
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val executive = req.session.getAttribute("executive") as Executive

        val name = req.getParameter("name")
        val location = req.getParameter("location")
        val start = LocalDateTime.parse(req.getParameter("start"))
        val end = LocalDateTime.parse(req.getParameter("end"))
        val aid = Integer.parseInt(req.getParameter("aid"))


    }
}