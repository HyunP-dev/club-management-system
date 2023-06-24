<%@ page import="kr.ac.hallym.clubmanagementsystem.repository.AttendanceRepository" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.model.Attendance" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.repository.MemberRepository" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.model.Member" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.repository.ActivityRepository" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.model.Activity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="right-panel-window">
    <h4 style="font-weight: bold;">활동 별 출석 관리</h4>
    <hr>
    <table id="attendance-table" class="table last-is-btns" width="100%">
        <thead>
        <tr>
            <th>활동명</th>
            <th>부원 이름</th>
            <th data-orderable="false"></th>
        </tr>
        </thead>
        <tbody>
        <%
            MemberRepository memberRepository = new MemberRepository();
            AttendanceRepository attendanceRepository = new AttendanceRepository();
            ActivityRepository activityRepository = new ActivityRepository();

            List<Member> members = memberRepository.findAll();
            List<Attendance> attendanceList = attendanceRepository.findAll();
            List<Activity> activities = activityRepository.findAll();

            for (Member member: members) {
                for (Activity activity: activities) {
        %>
            <tr>
                <td><%= activity.getName()%></td>
                <td><%= member.getName()%></td>
                <td>
                    <%
                        if (attendanceList.contains(new Attendance(activity.getAid(), member.getMid()))) {
                    %>
                    <span class="badge bg-success">출석</span>
                    <%
                        } else {
                    %>
                    <span class="badge bg-danger">결석</span>
                    <% } %>
                </td>
            </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
<script>
    $('#attendance-table').DataTable();
</script>
<span style="height: 5px; display: block;"></span>