<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="q" tagdir="/WEB-INF/tags/question" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@attribute name="question" required="true" type="by.bsuir.likeit.entity.Question" %>
<%@attribute name="user" required="true" type="by.bsuir.likeit.entity.User" %>

<c:if test="${user.id == question.author.id}">
    <a class="btn btn-outline-primary" onclick="editQuestion()" style="min-width: 100px;"
    id="edit"><spring:message code="question.edit"/></a>
    <button class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteQuestionModal" style="min-width: 100px;"><fmt:message
        key="question.delete"/></button>
    <q:deleteQuestionModal question="${question}"/>
    <script>
        function editQuestion() {
            const title = document.getElementById('title');
            const body = document.getElementById('body');
            const edit = document.getElementById('edit');

            const isEditing = title.contentEditable === 'true';
            title.contentEditable = !isEditing;
            title.classList.toggle('border');
            body.contentEditable = !isEditing;
            body.classList.toggle('border');

            edit.innerText = isEditing ? '<spring:message code="question.edit"/>' : '<spring:message code="question.save"/>';
            edit.classList.toggle('btn-outline-primary');
            edit.classList.toggle('btn-outline-success');

            if (isEditing) {
                saveChanges(title.innerText, body.innerText);
            }
        }

        function saveChanges(title, body) {
            const form = new FormData();
            form.append('topicId', ${question.topic.id});
            form.append('title', title);
            form.append('text', body);

            const params = new URLSearchParams(form);
            fetch('/question/${question.id}?' + params.toString(), {
                method: 'PUT',
            }).then(response => {
                if (response.ok) {
                    location.reload();
                }
            });
        }
    </script>

</c:if>