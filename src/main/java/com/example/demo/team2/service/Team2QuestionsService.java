package com.example.demo.team2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.demo.team2.entity.Team2Questions;
import com.example.demo.team2.form.Team2QuestionForm;
import com.example.demo.team2.repository.Team2QuestionsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Team2QuestionsService {
	
	private final Team2QuestionsRepository questionsRepository;
	
	//全件取得
	public List<Team2Questions> findAll(){
		return questionsRepository.findAllByOrderByCreatedAtDesc();
	}
	
	public void save(Team2Questions question) {
		if (question.getQuestionId() != 0) {
			Team2Questions existing = questionsRepository.findById(question.getQuestionId())
					.orElseThrow(() -> new RuntimeException("問題が見つかりません"));
			 question.setCreatedAt(existing.getCreatedAt());
		}
		questionsRepository.save(question);
	}
	
	public Team2Questions findByQuestionId(int questionId){
		return questionsRepository.findById(questionId)
				.orElseThrow(() -> new RuntimeException("問題が見つかりません"));
	}
	
	public void delete(int questionId) {
		questionsRepository.deleteById(questionId);
	}
	
	//Formの値をEntityに詰め替え
	public Team2Questions convertToEntity(Team2QuestionForm team2QuestionForm, int userId) {	
		Team2Questions team2Questions = new Team2Questions();
		team2Questions.setUserId(userId);
		team2Questions.setFieldId(team2QuestionForm.getFieldId());
		team2Questions.setStudyName(team2QuestionForm.getStudyName());
		team2Questions.setQuestionType(team2QuestionForm.getQuestionType());
		team2Questions.setQuestionText(team2QuestionForm.getQuestionText());
		team2Questions.setChoiceA(team2QuestionForm.getChoiceA());
		team2Questions.setChoiceB(team2QuestionForm.getChoiceB());
		team2Questions.setChoiceC(team2QuestionForm.getChoiceC());
		team2Questions.setChoiceD(team2QuestionForm.getChoiceD());
		team2Questions.setCorrectAnswer(team2QuestionForm.getCorrectAnswer());
		team2Questions.setExplanation(team2QuestionForm.getExplanation());
		return team2Questions;
	}
	
	//Entityの値をFormに詰め替え
	public Team2QuestionForm convertToForm(Team2Questions team2Questions) {
		Team2QuestionForm team2QuestionForm = new Team2QuestionForm();
		team2QuestionForm.setFieldId(team2Questions.getFieldId());
		team2QuestionForm.setStudyName(team2Questions.getStudyName());
		team2QuestionForm.setQuestionType(team2Questions.getQuestionType());
		team2QuestionForm.setQuestionText(team2Questions.getQuestionText());
		team2QuestionForm.setChoiceA(team2Questions.getChoiceA());
		team2QuestionForm.setChoiceB(team2Questions.getChoiceB());
		team2QuestionForm.setChoiceC(team2Questions.getChoiceC());
		team2QuestionForm.setChoiceD(team2Questions.getChoiceD());
		team2QuestionForm.setCorrectAnswer(team2Questions.getCorrectAnswer());
		team2QuestionForm.setExplanation(team2Questions.getExplanation());
		return team2QuestionForm;
	}
	
	//選択肢に内容が入っているかチェック
	public void checkChoices(Team2QuestionForm team2QuestionForm, BindingResult result) {
		if(team2QuestionForm.getQuestionType().equals("選択式")) {
			if(team2QuestionForm.getChoiceA() == null || team2QuestionForm.getChoiceA().isEmpty()) {
				result.rejectValue("choiceA", "error.choiceA", "選択肢Aを入力してください。");
			}
			if(team2QuestionForm.getChoiceB() == null|| team2QuestionForm.getChoiceB().isEmpty()) {
				result.rejectValue("choiceB", "error.choiceB", "選択肢Bを入力してください。");
			}
			if(team2QuestionForm.getChoiceC() == null|| team2QuestionForm.getChoiceC().isEmpty()) {
				result.rejectValue("choiceC", "error.choiceC", "選択肢Cを入力してください。");
			}
			if(team2QuestionForm.getChoiceD() == null|| team2QuestionForm.getChoiceD().isEmpty()) {
				result.rejectValue("choiceD", "error.choiceD", "選択肢Dを入力してください。");
			}
		}
	}

	public int countByUserId(int userId) {
		return questionsRepository.countByUserId(userId);
	}
}
	
