package services;

import card.QuizCard;
import common.Result;
import common.ServiceResult;
import repository.CardRepository;

public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl() {
        cardRepository = CardRepository.getInstance();
    }

    @Override
    public Result addQuizCard(QuizCard quizCard) {
        // Some business logic checking here
        // ...

        cardRepository.addQuizCard(quizCard);
        Result result = new ServiceResult(true, "success", null);
        return result;
    }
}
