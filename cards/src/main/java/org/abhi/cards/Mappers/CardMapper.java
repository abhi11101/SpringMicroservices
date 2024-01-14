package org.abhi.cards.Mappers;

import org.abhi.cards.DTO.CardsDTO;
import org.abhi.cards.Entity.Cards;

public class CardMapper {

    public static Cards mapToCard(CardsDTO cardsDTO, Cards cards){

        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setMobileNumber(cardsDTO.getMobileNumber());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        cards.setAvailableAmount(cardsDTO.getAvailableAmount());
        cards.setAmountUsed(cardsDTO.getAmountUsed());

        return cards;
    }

    public static CardsDTO mapToDTO(Cards cards, CardsDTO cardsDTO){

        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setMobileNumber(cards.getMobileNumber());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        cardsDTO.setAvailableAmount(cards.getAvailableAmount());
        cardsDTO.setAmountUsed(cards.getAmountUsed());

        return cardsDTO;
    }
}
