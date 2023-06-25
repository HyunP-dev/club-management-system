package kr.ac.hallym.clubmanagementsystem.controller.member

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.ac.hallym.clubmanagementsystem.model.Executive
import kr.ac.hallym.clubmanagementsystem.repository.MemberRepository
import java.net.HttpURLConnection

@WebServlet(name = "kickMemberServlet", value = ["/kick-member"])
class KickMemberServlet : HttpServlet() {
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val executive = req.session.getAttribute("executive") as Executive

        val memberRepository = MemberRepository()

        val mid = Integer.parseInt(req.getParameter("mid"))
        val member = memberRepository.findBy(mid)!!
        if (member.cid == executive.cid) {
            executive.kick(member)
            resp.writer.println("success")
        } else {
            resp.sendError(HttpURLConnection.HTTP_FORBIDDEN)
        }
    }
}