package com.matchgetit.backend.util;

import com.matchgetit.backend.dto.StadiumDTO;
import com.matchgetit.backend.entity.StadiumEntity;
import com.matchgetit.backend.repository.StadiumRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class NearStadium { //DB데이터 중 제일 가까운 스타디움 가져오기
    private static double euclideanDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static StadiumEntity findNearestStadiums(List<StadiumEntity> stdList, String x, String y, int count) {
        stdList.sort(Comparator.comparingDouble(st ->
                euclideanDistance(Double.parseDouble(x), Double.parseDouble(y),
                        Double.parseDouble(st.getXCor()), Double.parseDouble(st.getYCor()))));
        return stdList.subList(0, Math.min(count, stdList.size())).get(0);//구장 리스트 중 x,y값을 받아와서 count 만큼 가까운 구장 리스트빼는 메소드
    }
}
