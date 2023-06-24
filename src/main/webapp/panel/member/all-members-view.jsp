<%@ page import="kr.ac.hallym.clubmanagementsystem.repository.MemberRepository" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.model.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.model.Executive" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="right-panel-window">
    <h4 style="font-weight: bold;">모든 부원 조회</h4>
    <hr>
    <table id="myTable" class="table last-is-btns" width="100%">
        <thead>
        <tr>
            <th></th>
            <th data-orderable="false"></th>
        </tr>
        </thead>
        <tbody>
        <%
            Executive executive = (Executive) session.getAttribute("executive");
            MemberRepository memberRepository = new MemberRepository();
            List<Member> members = memberRepository.findAll()
                    .stream()
                    .filter(member -> member.getCid() == executive.getCid())
                    .collect(Collectors.toList());
            for (Member member: members) {
        %>
                <tr>
                    <td><%= member.getName()%></td>
                    <td>
                        <button class="btn btn-tiny btn-danger"
                                onclick="kickMember(<%= member.getMid()%>)">강퇴</button>
                    </td>
                </tr>
        <%  } %>
        </tbody>
    </table>
</div>
<style>
    .btn-tiny {
        padding: .4rem .4rem;
        font-size: .875rem;
        line-height: .5;
        border-radius: .2rem;
    }
</style>
<script>
    $('#myTable').DataTable();
</script>
<span style="height: 5px; display: block;"></span>

<script>
    function kickMember(mid) {
        let modal = document.getElementById("kick-modal")
        modal.getElementById("midInput").value = mid
        new bootstrap.Modal(modal).show()
    }
</script>

<div class="modal fade" id="kick-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">정말로 동아리로부터 내보내시겠습니까?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <!--            <div class="modal-body">-->
            <!--                <form>-->

            <!--                </form>-->
            <!--            </div>-->
            <div class="modal-footer">
                <form method="post" action="">
                    <input id="midInput" name="mid" value="" hidden="hidden">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소하기</button>
                    <button type="submit" class="btn btn-danger">내보내기</button>
                </form>
            </div>
        </div>
    </div>
</div>
