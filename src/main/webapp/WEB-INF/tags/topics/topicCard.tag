<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/topics" prefix="t" %>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="date" %>

<%@attribute name="topic" required="true" type="by.bsuir.likeit.entity.Topic" %>
<%@attribute name="questions" required="true" type="java.util.List<by.bsuir.likeit.entity.Question>" %>
<div class="card mb-3">
    <div class="card-header d-flex gap-4 align-items-start">
        <h3 class="card-title"><a href="<c:url value="/topic/${topic.id}"/>" class="text-decoration-none"><c:out value="${topic.name}"/></a></h3>
        <div class="d-flex ms-auto gap-2 align-items-end">
            <common:askButton topic="${topic}"/>
            <t:topicActionsButtons topic="${topic}"/>
        </div>
    </div>
    <div class="card-body">
        <div class="d-flex flex-nowrap overflow-auto">
            <c:forEach var="question" items="${questions}">
                <t:topicQuestionCard question="${question}"/>
            </c:forEach>
        </div>
    </div>
    <div class="card-footer text-muted d-flex gap-4 align-items-end">
        <div class="small text-muted"><spring:message code="topic.creationTime"/>: <c:out value="${d:formatLocalDateTime(topic.creationTime)}"/></div>
        <div class="small text-muted"><spring:message code="topic.questionsCount"/>: <c:out value="${topic.questionsCount}"/></div>
        <div class="d-flex ms-auto gap-2 align-items-end">
            <div class="small text-muted"><spring:message code="topic.author"/>: </div>
            <a href="<c:url value="/profile/${topic.author.id}"/>" class="link-danger text-decoration-none lead" style="line-height: 1.2;"><c:out value="${topic.author.username}"/></a>
        </div>
    </div>
</div>