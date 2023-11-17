package org.kainovk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PalindromeGame {

    private final Map<User, Set<String>> usedPhrasesByUser;
    private final List<User> leaderboard;

    public PalindromeGame() {
        this.usedPhrasesByUser = new HashMap<>();
        this.leaderboard = new ArrayList<>();
    }

    public void processPhrase(String phrase, User user) {
        if (!isPalindrome(phrase)) {
            return;
        }

        if (!usedPhrasesByUser.containsKey(user)) {
            usedPhrasesByUser.put(user, new HashSet<>());
        }

        if (!usedPhrasesByUser.get(user).contains(phrase)) {
            int length = phrase.length();
            user.addPoints(length);

            usedPhrasesByUser.get(user).add(phrase);
            updateLeaderboard(user);
        }
    }

    public boolean isPalindrome(String phrase) {
        String cleanPhrase = phrase.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int length = cleanPhrase.length();

        for (int i = 0; i < length / 2; i++) {
            if (cleanPhrase.charAt(i) != cleanPhrase.charAt(length - i - 1)) {
                return false;
            }
        }

        return true;
    }

    private void updateLeaderboard(User user) {
        leaderboard.removeIf(u -> u.getName().equals(user.getName()));
        leaderboard.add(user);
        leaderboard.sort(Comparator.comparingInt(User::getPoints).reversed());

        if (leaderboard.size() > 5) {
            leaderboard.remove(5);
        }
    }

    public List<User> getLeaderboard() {
        return leaderboard;
    }
}
