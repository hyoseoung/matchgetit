package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.RankDTO;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {

    private final MemberRepository memberRepository;

    public String gradeComf(Integer rating){
        String grade ="";
        if(rating>=1100) {
            grade = "Professional";
        } else if (rating>=701){
            grade = "Advanced";
        } else if (rating>=401){
            grade = "Middle";
        } else {
            grade = "Beginner";
        }
        return grade;
    }


    public String VicRatingComf(Long win,Long lose){
        int vicRating;
        vicRating = Integer.parseInt(String.valueOf(win))*100/(Integer.parseInt(String.valueOf(win))+Integer.parseInt(String.valueOf(lose)));
        return String.valueOf(vicRating);
    }

    public List<RankDTO> getRankList() {
        List<MemberEntity> rankEntityList = memberRepository.findAll(Sort.by(Sort.Direction.DESC,"rating"));
        List<RankDTO> rankDTOList = new ArrayList<>();

        for(int i=0; i<rankEntityList.size();i++){
            MemberEntity rE = rankEntityList.get(i);
            String grade = gradeComf(Math.toIntExact(rE.getRating()));
            Long win = rE.getWin();
            Long lose = rE.getLose();
            if (win == null || lose == null) {
                if(win == null && lose == null){
                    RankDTO rankDTO = new RankDTO(rE.getUserId(), grade, rE.getName(),
                            rE.getRating(), 0L, 0L,"0%",i+1);
                    rankDTOList.add(rankDTO);
                } else if(lose == null) {
                    RankDTO rankDTO = new RankDTO(rE.getUserId(), grade, rE.getName(),
                            rE.getRating(), rE.getWin(), 0L,"0%",i+1);
                    rankDTOList.add(rankDTO);
                } else if (win == null) {
                    RankDTO rankDTO = new RankDTO(rE.getUserId(), grade, rE.getName(),
                            rE.getRating(), 0L, rE.getLose(), "0%",i+1);
                    rankDTOList.add(rankDTO);
                }
            } else {
                RankDTO rankDTO = new RankDTO(rE.getUserId(), grade, rE.getName(),
                        rE.getRating(), rE.getWin(), rE.getLose(), VicRatingComf(rE.getWin(),
                        rE.getLose())+"%",i+1);
                rankDTOList.add(rankDTO);
            }
        }
        return rankDTOList;
    }
}
