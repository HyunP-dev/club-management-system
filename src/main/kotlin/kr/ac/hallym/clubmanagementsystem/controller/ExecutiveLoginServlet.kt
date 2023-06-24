package kr.ac.hallym.clubmanagementsystem.controller

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.ac.hallym.clubmanagementsystem.repository.ClubRepository
import kr.ac.hallym.clubmanagementsystem.repository.ExecutiveRepository
import java.net.HttpURLConnection

@WebServlet(name = "executiveLoginServlet", value = ["/executive-login"])
class ExecutiveLoginServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val schoolName = req.getParameter("school")
        val clubName = req.getParameter("club")
        val username = req.getParameter("username")
        val password = req.getParameter("password")
        val club = ClubRepository().findAll()
            .find { it.school == schoolName && it.name == clubName }

        val executiveRepository = ExecutiveRepository()
        if ((club == null)) {
            resp.sendError(HttpURLConnection.HTTP_FORBIDDEN)
            return
        }
        val executive = executiveRepository.findBy(club, username)
        if (executive?.password != password) {
            resp.sendError(HttpURLConnection.HTTP_FORBIDDEN)
            return
        }
        req.session.setAttribute("executive", executive!!)
    }
}