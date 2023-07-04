package com.matchgetit.backend.service;

import com.matchgetit.backend.entity.MatchEntity;
import com.matchgetit.backend.entity.MatchWaitEntity;
import com.matchgetit.backend.entity.PartyEntity;
import com.matchgetit.backend.repository.MatchRepository;
import com.matchgetit.backend.repository.MatchWaitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPageMatchService {
    private final MatchRepository matchRepository;
    private final MatchWaitRepository matchWaitRepository;

    public void createMatches() {
        for (int i=0; i<10; i++) {
            MatchWaitEntity game = new MatchWaitEntity();
//            game.setTeam();
        }
    }
}
