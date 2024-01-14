package org.abhi.cards.Service.ServiceImpl;

import lombok.AllArgsConstructor;
import org.abhi.cards.Constants.CardsConstants;
import org.abhi.cards.DTO.CardsDTO;
import org.abhi.cards.Entity.Cards;
import org.abhi.cards.Mappers.CardMapper;
import org.abhi.cards.Repositories.CardRepo;
import org.abhi.cards.Service.CardService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepo cardRepo;


    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardRepo.findByMobileNumber(mobileNumber);
        if (cards.isPresent()){

        }
        cardRepo.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return
     */
    @Override
    public CardsDTO fetchCard(String mobileNumber) {
        Cards cards = cardRepo.findByMobileNumber(mobileNumber).orElseThrow();

        return CardMapper.mapToDTO(cards, new CardsDTO());
    }

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return
     */
    @Override
    public boolean updateCard(CardsDTO cardsDto) {
        Cards cards = cardRepo.findByCardNumber(cardsDto.getCardNumber()).orElseThrow();
        CardMapper.mapToCard(cardsDto,cards);
        cardRepo.save(cards);
        return true;
    }

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardRepo.findByMobileNumber(mobileNumber).orElseThrow();
        cardRepo.deleteById(cards.getCardId());
        return true;
    }
}
