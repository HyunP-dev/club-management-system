<%@ page import="kr.ac.hallym.clubmanagementsystem.repository.ActivityRepository" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.model.Executive" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="kr.ac.hallym.clubmanagementsystem.model.Activity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="right-panel-window">
    <h4 style="font-weight: bold;">모든 동아리 활동 조회</h4>
    <hr>
    <table id="activities-table" class="table last-is-btns" width="100%">
        <thead>
        <tr>
            <th>활동명</th>
            <th>장소</th>
            <th>시작 시간</th>
            <th>종료 시간</th>
            <th data-orderable="false"></th>
        </tr>
        </thead>
        <tbody>
        <%
//            Executive executive = (Executive) session.getAttribute("executive");
            Executive executive = new Executive(1, "Alice", "1234");
            session.setAttribute("executive", executive);
            ActivityRepository activityRepository = new ActivityRepository();
            List<Activity> activities = activityRepository.findAll()
                    .stream()
                    .filter(activity -> activity.getCid() == executive.getCid())
                    .collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.KOREA);
            for (Activity activity : activities) {
                String name = activity.getName();
                String location = activity.getLocation();
                String start = formatter.format(activity.getStart());
                String end = formatter.format(activity.getEnd());
        %>
        <tr>
            <td><%= name%></td>
            <td><%= location%></td>
            <td><%= start%></td>
            <td><%= end%></td>
            <td>
                <button class="btn btn-tiny btn-secondary"
                        onclick="modifyActivity(<%=activity.getAid()%>, '<%= name%>', '<%= location%>', '<%= start%>', '<%= end%>')">
                    편집
                </button>
                <button class="btn btn-tiny btn-danger" onclick="deleteActivity(<%= activity.getAid()%>)">삭제</button>
            </td>
        </tr>
        <% }%>
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
    $('#activities-table').DataTable()
</script>
<span style="height: 5px; display: block;"></span>

<script>
    function modifyActivity(aid, name, location, start, end) {
        let modal = document.getElementById("modify-activity-modal")
        let input = {
            name: modal.querySelector("input[name='activity-name']"),
            location: modal.querySelector("input[name='activity-location']"),
            start: modal.querySelector("input[name='activity-start']"),
            end: modal.querySelector("input[name='activity-end']"),
            aid: modal.querySelector("input[name='aid']")
        }
        input.name.value = name
        input.location.value = location
        input.start.value = start
        input.end.value = end
        input,aid.value = aid
        new bootstrap.Modal(modal).show()
    }

    function deleteActivity(aid) {
        let modal = document.getElementById("delete-activity-modal")
        modal.querySelector("input[name='aid']").value = aid
        new bootstrap.Modal(modal).show()
    }
</script>

<div class="modal fade" id="modify-activity-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">활동 편집</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="modify-activity" method="post">
                    활동명: <input name="activity-name" class="form-control" style="margin-bottom: 12px;">
                    장소: <input name="activity-location" class="form-control" style="margin-bottom: 12px;">
                    시작 시간:
                    <input name="activity-start" type="datetime-local" class="form-control"
                           style="margin-bottom: 12px;">
                    종료 시간:
                    <input name="activity-end" type="datetime-local" class="form-control" style="margin-bottom: 12px;">

                    <input name="aid" value="" hidden="hidden">
                    <input name="cid" value="" hidden="hidden">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소하기</button>
                <button type="button" class="btn btn-primary">저장하기</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="delete-activity-modal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">정말로 삭제하시겠습니까?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-footer">
                <form method="post" action="delete-activity">
                    <input name="aid" value="" hidden="hidden">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소하기</button>
                    <button type="submit" class="btn btn-danger">삭제하기</button>
                </form>
            </div>
        </div>
    </div>
</div>