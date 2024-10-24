package by.itminsk.cyclingclubbackend.mapper.survey;

import by.itminsk.cyclingclubbackend.model.answer.Answer;
import by.itminsk.cyclingclubbackend.model.answer.dto.AnswerResponse;
import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.question.dto.CreateQuestionRequest;
import by.itminsk.cyclingclubbackend.model.question.dto.QuestionResponse;
import by.itminsk.cyclingclubbackend.model.question.dto.UpdateQuestionRequest;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static Question mapToQuestion(CreateQuestionRequest request) {
        if (request == null) {
            return null; // или выбросьте исключение
        }

        Question question = new Question();
        question.setQuestion(request.question());
        question.setAllowMultipleAnswer(request.allowMultipleAnswer());
        question.setIsRequired(request.isRequired());

        // Обработка ответов и установление связи
        if (request.answers() != null) {
            List<Answer> answerList = request.answers().stream()
                    .map(AnswerMapper::mapToAnswer)
                    .peek(answer -> answer.setQuestion(question)) // Устанавливаем связь с вопросом
                    .collect(Collectors.toList());
            question.setAnswers(answerList);
        }

        return question;
    }

    public static Question mapToQuestion(UpdateQuestionRequest request) {
        if (request == null) {
            return null;
        }

        Question question = new Question();
        question.setId(request.id()); // Получаем id для обновления
        question.setQuestion(request.question());
        question.setAllowMultipleAnswer(request.allowMultipleAnswer());
        question.setIsRequired(request.isRequired());

        // Обработка ответов и установление связи
        if (request.answers() != null) {
            List<Answer> answerList = request.answers().stream()
                    .map(AnswerMapper::mapToAnswer)
                    .peek(answer -> answer.setQuestion(question)) // Устанавливаем связь с вопросом
                    .collect(Collectors.toList());
            question.setAnswers(answerList);
        }

        return question;
    }

    public static QuestionResponse questionToQuestionResponse(Question question) {
        List<AnswerResponse> answerResponses = question.getAnswers().stream()
                .map(AnswerMapper::answerToAnswerResponse)
                .collect(Collectors.toList());

        return new QuestionResponse(
                question.getId(),
                question.getQuestion(),
                question.getAllowMultipleAnswer(),
                question.getIsRequired(),
                answerResponses
        );
    }
}
