package com.kpi.services;

import com.kpi.entities.Card;
import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.model.UsersOnlineDao;

public class UserService {

    public User getAuthorizedUser(String token1){
        UsersDao usersDao = new UsersDao();
        int userId = UsersOnlineDao.getUserId(token1);
        return usersDao.getById(userId);
    }

    public boolean checkIsAdmin(User user) {
        return user.isAdmin();
    }

    public User loginUser(String email, String pw) {
        UsersDao usersDao = new UsersDao();
        return usersDao.getUserByLogin(email, pw);
    }

    public Card getCardByNum(String num, User user){
        Card card = null;

        for (Card c: user.getCards()  ) {
            if(c.getCardNum().equals("num")){
                card = c;
                break;
            }
        }

        return card;
    }

    public String generateToken() {
        String symbols = "qwertyasdhfgsdg";
        StringBuilder randString = new StringBuilder();
        int count = 10;
        for(int i=0;i<count;i++)
            randString.append(symbols.charAt((int)(Math.random()*symbols.length())));
        return String.valueOf(randString);
    }
}
