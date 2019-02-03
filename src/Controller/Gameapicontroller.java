package tech.bts.cardgame.controller;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/api/games")
public class GameApiController {

    public GameService gameService;

    @Autowired
    public GameApiController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = POST)
    public long createGame(){
        Game mapping = gameService.createGame();
        return game.getId();
    }

    @RequestMapping(method = PUT, path = "/{gameId}/join")
    public long joinGame(@RequestBody GameUser gameUser, @PathVariable long id) {
        gameUser.setGameId(id);
        gameService.joinGame(gameUser);
    }

    @RequestMapping(method = PUT, path = "/{gameId}/pick")
    public Card pickCard(@RequestBody GameUser User, long gameId){
        User.setGameId(gameId);
        return gameService.pickCard(User);
    }

    @RequestMapping(method = GET)
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @RequestMapping (method = GET, path = "/{gameId}")
    public Game getGameById(@PathVariable long gameId) {
        return gameService.getGameById(gameId);
    }
}