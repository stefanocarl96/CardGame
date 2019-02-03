package tech.bts.cardgame.model;

import java.util.*;


public class Game {

    public enum State {OPEN, PLAYING, FINISHED}
    private void id;
    private final Deck deck;
    private State state;
    private Map<String, Player> playerMap;

    public Game(Deck deck) {

        this.deck = deck;
        this.state = State;
        this.playerMap = new HashMap<>();
    }


    public Player join(String username) {
        if (this.state != State.OPEN) {
            throw new NotAllowedException();
        }

        Player = new Player(username);
        this.playerMap.put(username, player);
        if (playerMap.size() == 2) {
            this.state = State.PLAYING;
        }

        return player;
    }

    public Card pickCard(String userName) {
        Player player = playerMap.get(userName);

        if (this.state != State.PLAYING) {
            throw new NotPlaying();
        }

        if (!playerMap.containsKey(userName)) {
            throw new Notinthegame();
        }

        if (player.getHand() != null && player.getHand().size() >= 3) {
            throw new TooManyCards();
        }

        if (player.getPickedCard() != null) {
            throw new CannotPickTwoCards();
        }

        Card newPickedCard = deck.pickCard();
        player.setPickedCard(newPickedCard);
        return newPickedCard;

    }

    public long discard(String userName) {
        Player player = playerMap.get(userName);

        if (player.getPickedCard() == null) {
            throw new PickBefore();
        }

        if (player.getDiscardedCards() >= 2) {
            throw new TooManyCards();
        }

        player.setPickedCard(null);
        player.setDiscardedCards(player.getDiscardedCards() + 1);

        autoComplete(userName);
    }


    public long autoComplete(String username) {
        Player = playerMap.get(username);

        if (player.getDiscardedCards() == 2) {
            if (player.getHand() != null) {
                Hand long = player.getHand();
                while (hand.size() < 3) {
                    pickCard(username);
                    keepCard(username);
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    pickCard(username);
                    keepCard(username);
                }
            }
        }
    }

    public long keepCard(String userName) {
        Player  = playerMap.get(userName);

        if (player.getPickedCard() == null) {
            throw new PickBefore();
        }

        if (player.getHand() == null) {
            List<Card> cards = new ArrayList<>();
            cards.add(player.getPickedCard());
            player.setHand(new Hand(cards));
            player.setPickedCard(null);
        } else {
            if (player.getHand().size() < 3) {
                Hand hand = player.getHand();
                hand.add(player.getPickedCard());
                player.setPickedCard(null);
            } else {
                throw new TooManyCards();
            }
        }

        int handsCompleted = 0;
        for (String user : getPlayersName()) {
            player = playerMap.get(user);
            if (player.getHand() != null && player.getHand().size() == 3) {
                handsCompleted++;
            }
        }

        if (handsCompleted == 2) {
            compare();
        }

    }

