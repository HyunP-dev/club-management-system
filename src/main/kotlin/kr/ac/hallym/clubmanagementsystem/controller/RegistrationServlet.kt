package kr.ac.hallym.clubmanagementsystem.controller

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.ac.hallym.clubmanagementsystem.model.Club
import kr.ac.hallym.clubmanagementsystem.model.Executive
import kr.ac.hallym.clubmanagementsystem.repository.ClubRepository
import kr.ac.hallym.clubmanagementsystem.repository.ExecutiveRepository
import java.sql.SQLException


@WebServlet(name = "registrationServlet", value = ["/registration"])
class RegistrationServlet : HttpServlet() {
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val school = req.getParameter("school")
        val club = req.getParameter("club")
        val username = req.getParameter("username")
        val password = req.getParameter("password")

        val clubRepository = ClubRepository()
        val executiveRepository = ExecutiveRepository()

        try {
            val clubObj = clubRepository.findBy(school, club).let {
                if (it == null) {
                    clubRepository.save(
                        Club(
                            cid = null,
                            school = school,
                            name = club
                        )
                    )
                    clubRepository.findBy(school, club)
                } else {
                    it
                }
            }!!
            executiveRepository.save(
                Executive(
                    cid = clubObj.cid!!,
                    username = username,
                    password = password
                )
            )
        } catch (e: SQLException) {
            req.session.setAttribute("registration-error", true)
            resp.sendRedirect("/registration.html")
        }
        resp.sendRedirect("/")
    }
}

