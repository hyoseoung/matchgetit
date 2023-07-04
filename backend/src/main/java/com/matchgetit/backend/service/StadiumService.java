package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.StadiumDTO;
import com.matchgetit.backend.entity.StadiumEntity;
import com.matchgetit.backend.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    private final ModelMapper modelMapper;

    public Page<StadiumEntity> findAllStadiums(Pageable pageable) {
        return stadiumRepository.findAll(pageable);
    }

    public Page<StadiumEntity> stadiumSearchList(String searchKeyword,Pageable pageable){
        return stadiumRepository.findByStdNameContaining(searchKeyword,pageable);
    }

    public void addStadium(StadiumDTO stadiumDTO) {
        String stdName = stadiumDTO.getStdName();
        // 이미 존재하는 구장인지 확인
        if (stadiumRepository.findByStdName(stdName) != null) {
            throw new RuntimeException("이미 존재하는 구장입니다.");
        }
        StadiumEntity stadium = modelMapper.map(stadiumDTO, StadiumEntity.class);
        stadiumRepository.save(stadium);
    }

    public Optional<StadiumDTO> StadiumOne(String stdName) {
        Optional<Object[]> result = stadiumRepository.selectOne(stdName).stream().findAny();
        return result.map(objects -> {
            StadiumDTO stadiumDTO = new StadiumDTO();
            stadiumDTO.setStdName((String) objects[0]);
            stadiumDTO.setStdLink((String) objects[1]);
            stadiumDTO.setStdPn((String) objects[2]);
            stadiumDTO.setStdImgUrl((String) objects[3]);
            stadiumDTO.setStdAddress((String) objects[4]);
            stadiumDTO.setXCor((String) objects[5]);
            stadiumDTO.setYCor((String) objects[6]);
            return stadiumDTO;
        });
    }

    @Transactional
    public void deleteStadium(String stdName) {
        stadiumRepository.deleteByStdName(stdName);
    }

    public void updateStadium(String stdName, StadiumDTO stadiumDTO) {
        stadiumDTO.setStdName(stdName);
        String stdLink = stadiumDTO.getStdLink();
        String stdPn = stadiumDTO.getStdPn();
        String stdImgUrl = stadiumDTO.getStdImgUrl();
        String stdAddress = stadiumDTO.getStdAddress();
        String xCor = stadiumDTO.getXCor();
        String yCor = stadiumDTO.getYCor();
        stadiumRepository.updateByStdName(stdName,stdLink, stdPn, stdImgUrl, stdAddress, xCor, yCor);
    }



    public List<StadiumDTO> findAllStadium(){
        return stadiumRepository.findAll().stream().map(s->modelMapper.map(s,StadiumDTO.class)).toList();
    }

    public void insertStadium() {
        StadiumEntity StadiumEntity1 = new StadiumEntity();
        StadiumEntity1.setMngId("???");
        StadiumEntity1.setStdName("풋살장 2");
        StadiumEntity1.setStdLink("http://www.example.com/2");
        StadiumEntity1.setXCor("37.551385");
        StadiumEntity1.setYCor("126.976516");
        StadiumEntity1.setStdAddress("서울특별시 풋살구장 2번");
        StadiumEntity1.setStdStart("10:00");
        StadiumEntity1.setStdEnd("22:00");
        StadiumEntity1.setStdPn("010-9876-5432");
        StadiumEntity1.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity1.setStdMng("???");
        StadiumEntity1.setStdDetails("풋살장에 대한 상세 정보 2");

        StadiumEntity StadiumEntity2 = new StadiumEntity();
        StadiumEntity2.setMngId("???");
        StadiumEntity2.setStdName("풋살장 3");
        StadiumEntity2.setStdLink("http://www.example.com/3");
        StadiumEntity2.setXCor("37.558782");
        StadiumEntity2.setYCor("126.953595");
        StadiumEntity2.setStdAddress("서울특별시 풋살구장 3번");
        StadiumEntity2.setStdStart("10:00");
        StadiumEntity2.setStdEnd("22:00");
        StadiumEntity2.setStdPn("010-5555-5555");
        StadiumEntity2.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity2.setStdMng("???");
        StadiumEntity2.setStdDetails("풋살장에 대한 상세 정보 3");

// 나머지 StadiumEntity 객체 생성 및 설정
        StadiumEntity StadiumEntity3 = new StadiumEntity();
        StadiumEntity3.setMngId("???");
        StadiumEntity3.setStdName("풋살장 4");
        StadiumEntity3.setStdLink("http://www.example.com/4");
        StadiumEntity3.setXCor("37.528635");
        StadiumEntity3.setYCor("126.977918");
        StadiumEntity3.setStdAddress("서울특별시 풋살구장 4번");
        StadiumEntity3.setStdStart("10:00");
        StadiumEntity3.setStdEnd("22:00");
        StadiumEntity3.setStdPn("010-1234-5678");
        StadiumEntity3.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity3.setStdMng("???");
        StadiumEntity3.setStdDetails("풋살장에 대한 상세 정보 4");

        StadiumEntity StadiumEntity4 = new StadiumEntity();
        StadiumEntity4.setMngId("???");
        StadiumEntity4.setStdName("풋살장 5");
        StadiumEntity4.setStdLink("http://www.example.com/5");
        StadiumEntity4.setXCor("37.507607");
        StadiumEntity4.setYCor("126.942913");
        StadiumEntity4.setStdAddress("서울특별시 풋살구장 5번");
        StadiumEntity4.setStdStart("10:00");
        StadiumEntity4.setStdEnd("22:00");
        StadiumEntity4.setStdPn("010-9876-5432");
        StadiumEntity4.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity4.setStdMng("???");
        StadiumEntity4.setStdDetails("풋살장에 대한 상세 정보 5");

        StadiumEntity StadiumEntity5 = new StadiumEntity();
        StadiumEntity5.setMngId("???");
        StadiumEntity5.setStdName("풋살장 6");
        StadiumEntity5.setStdLink("http://www.example.com/6");
        StadiumEntity5.setXCor("37.505211");
        StadiumEntity5.setYCor("126.928082");
        StadiumEntity5.setStdAddress("서울특별시 풋살구장 6번");
        StadiumEntity5.setStdStart("10:00");
        StadiumEntity5.setStdEnd("22:00");
        StadiumEntity5.setStdPn("010-5555-5555");
        StadiumEntity5.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity5.setStdMng("???");
        StadiumEntity5.setStdDetails("풋살장에 대한 상세 정보 6");

        StadiumEntity StadiumEntity6 = new StadiumEntity();
        StadiumEntity6.setMngId("???");
        StadiumEntity6.setStdName("풋살장 7");
        StadiumEntity6.setStdLink("http://www.example.com/7");
        StadiumEntity6.setXCor("37.492704");
        StadiumEntity6.setYCor("126.918034");
        StadiumEntity6.setStdAddress("서울특별시 풋살구장 7번");
        StadiumEntity6.setStdStart("10:00");
        StadiumEntity6.setStdEnd("22:00");
        StadiumEntity6.setStdPn("010-1234-5678");
        StadiumEntity6.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity6.setStdMng("???");
        StadiumEntity6.setStdDetails("풋살장에 대한 상세 정보 7");

        StadiumEntity StadiumEntity7 = new StadiumEntity();
        StadiumEntity7.setMngId("???");
        StadiumEntity7.setStdName("풋살장 8");
        StadiumEntity7.setStdLink("http://www.example.com/8");
        StadiumEntity7.setXCor("37.493031");
        StadiumEntity7.setYCor("126.884657");
        StadiumEntity7.setStdAddress("서울특별시 풋살구장 8번");
        StadiumEntity7.setStdStart("10:00");
        StadiumEntity7.setStdEnd("22:00");
        StadiumEntity7.setStdPn("010-9876-5432");
        StadiumEntity7.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity7.setStdMng("???");
        StadiumEntity7.setStdDetails("풋살장에 대한 상세 정보 8");

        StadiumEntity StadiumEntity8 = new StadiumEntity();
        StadiumEntity8.setMngId("???");
        StadiumEntity8.setStdName("풋살장 9");
        StadiumEntity8.setStdLink("http://www.example.com/9");
        StadiumEntity8.setXCor("37.498935");
        StadiumEntity8.setYCor("126.847388");
        StadiumEntity8.setStdAddress("서울특별시 풋살구장 9번");
        StadiumEntity8.setStdStart("10:00");
        StadiumEntity8.setStdEnd("22:00");
        StadiumEntity8.setStdPn("010-5555-5555");
        StadiumEntity8.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity8.setStdMng("???");
        StadiumEntity8.setStdDetails("풋살장에 대한 상세 정보 9");

        StadiumEntity StadiumEntity9 = new StadiumEntity();
        StadiumEntity9.setMngId("???");
        StadiumEntity9.setStdName("풋살장 10");
        StadiumEntity9.setStdLink("http://www.example.com/10");
        StadiumEntity9.setXCor("37.503569");
        StadiumEntity9.setYCor("126.818605");
        StadiumEntity9.setStdAddress("서울특별시 풋살구장 10번");
        StadiumEntity9.setStdStart("10:00");
        StadiumEntity9.setStdEnd("22:00");
        StadiumEntity9.setStdPn("010-1234-5678");
        StadiumEntity9.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity9.setStdMng("???");
        StadiumEntity9.setStdDetails("풋살장에 대한 상세 정보 10");

        StadiumEntity StadiumEntity10 = new StadiumEntity();
        StadiumEntity10.setMngId("???");
        StadiumEntity10.setStdName("풋살장 11");
        StadiumEntity10.setStdLink("http://www.example.com/11");
        StadiumEntity10.setXCor("37.516824");
        StadiumEntity10.setYCor("126.808398");
        StadiumEntity10.setStdAddress("서울특별시 풋살구장 11번");
        StadiumEntity10.setStdStart("10:00");
        StadiumEntity10.setStdEnd("22:00");
        StadiumEntity10.setStdPn("010-9876-5432");
        StadiumEntity10.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity10.setStdMng("???");
        StadiumEntity10.setStdDetails("풋살장에 대한 상세 정보 11");

        StadiumEntity StadiumEntity11 = new StadiumEntity();
        StadiumEntity11.setMngId("???");
        StadiumEntity11.setStdName("풋살장 12");
        StadiumEntity11.setStdLink("http://www.example.com/12");
        StadiumEntity11.setXCor("37.533891");
        StadiumEntity11.setYCor("126.807019");
        StadiumEntity11.setStdAddress("서울특별시 풋살구장 12번");
        StadiumEntity11.setStdStart("10:00");
        StadiumEntity11.setStdEnd("22:00");
        StadiumEntity11.setStdPn("010-5555-5555");
        StadiumEntity11.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity11.setStdMng("???");
        StadiumEntity11.setStdDetails("풋살장에 대한 상세 정보 12");

        StadiumEntity StadiumEntity12 = new StadiumEntity();
        StadiumEntity12.setMngId("???");
        StadiumEntity12.setStdName("풋살장 13");
        StadiumEntity12.setStdLink("http://www.example.com/13");
        StadiumEntity12.setXCor("37.546792");
        StadiumEntity12.setYCor("126.813186");
        StadiumEntity12.setStdAddress("서울특별시 풋살구장 13번");
        StadiumEntity12.setStdStart("10:00");
        StadiumEntity12.setStdEnd("22:00");
        StadiumEntity12.setStdPn("010-1234-5678");
        StadiumEntity12.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity12.setStdMng("???");
        StadiumEntity12.setStdDetails("풋살장에 대한 상세 정보 13");

        StadiumEntity StadiumEntity13 = new StadiumEntity();
        StadiumEntity13.setMngId("???");
        StadiumEntity13.setStdName("풋살장 14");
        StadiumEntity13.setStdLink("http://www.example.com/14");
        StadiumEntity13.setXCor("37.562961");
        StadiumEntity13.setYCor("126.819643");
        StadiumEntity13.setStdAddress("서울특별시 풋살구장 14번");
        StadiumEntity13.setStdStart("10:00");
        StadiumEntity13.setStdEnd("22:00");
        StadiumEntity13.setStdPn("010-9876-5432");
        StadiumEntity13.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity13.setStdMng("???");
        StadiumEntity13.setStdDetails("풋살장에 대한 상세 정보 14");

        StadiumEntity StadiumEntity14 = new StadiumEntity();
        StadiumEntity14.setMngId("???");
        StadiumEntity14.setStdName("풋살장 15");
        StadiumEntity14.setStdLink("http://www.example.com/15");
        StadiumEntity14.setXCor("37.581763");
        StadiumEntity14.setYCor("126.829602");
        StadiumEntity14.setStdAddress("서울특별시 풋살구장 15번");
        StadiumEntity14.setStdStart("10:00");
        StadiumEntity14.setStdEnd("22:00");
        StadiumEntity14.setStdPn("010-5555-5555");
        StadiumEntity14.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity14.setStdMng("???");
        StadiumEntity14.setStdDetails("풋살장에 대한 상세 정보 15");

        StadiumEntity StadiumEntity15 = new StadiumEntity();
        StadiumEntity15.setMngId("???");
        StadiumEntity15.setStdName("풋살장 16");
        StadiumEntity15.setStdLink("http://www.example.com/16");
        StadiumEntity15.setXCor("37.600145");
        StadiumEntity15.setYCor("126.842638");
        StadiumEntity15.setStdAddress("서울특별시 풋살구장 16번");
        StadiumEntity15.setStdStart("10:00");
        StadiumEntity15.setStdEnd("22:00");
        StadiumEntity15.setStdPn("010-1234-5678");
        StadiumEntity15.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity15.setStdMng("???");
        StadiumEntity15.setStdDetails("풋살장에 대한 상세 정보 16");

        StadiumEntity StadiumEntity16 = new StadiumEntity();
        StadiumEntity16.setMngId("???");
        StadiumEntity16.setStdName("풋살장 17");
        StadiumEntity16.setStdLink("http://www.example.com/17");
        StadiumEntity16.setXCor("37.616433");
        StadiumEntity16.setYCor("126.858856");
        StadiumEntity16.setStdAddress("서울특별시 풋살구장 17번");
        StadiumEntity16.setStdStart("10:00");
        StadiumEntity16.setStdEnd("22:00");
        StadiumEntity16.setStdPn("010-9876-5432");
        StadiumEntity16.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity16.setStdMng("???");
        StadiumEntity16.setStdDetails("풋살장에 대한 상세 정보 17");

        StadiumEntity StadiumEntity17 = new StadiumEntity();
        StadiumEntity17.setMngId("???");
        StadiumEntity17.setStdName("풋살장 18");
        StadiumEntity17.setStdLink("http://www.example.com/18");
        StadiumEntity17.setXCor("37.636356");
        StadiumEntity17.setYCor("126.869352");
        StadiumEntity17.setStdAddress("서울특별시 풋살구장 18번");
        StadiumEntity17.setStdStart("10:00");
        StadiumEntity17.setStdEnd("22:00");
        StadiumEntity17.setStdPn("010-5555-5555");
        StadiumEntity17.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity17.setStdMng("???");
        StadiumEntity17.setStdDetails("풋살장에 대한 상세 정보 18");

        StadiumEntity StadiumEntity18 = new StadiumEntity();
        StadiumEntity18.setMngId("???");
        StadiumEntity18.setStdName("풋살장 19");
        StadiumEntity18.setStdLink("http://www.example.com/19");
        StadiumEntity18.setXCor("37.654539");
        StadiumEntity18.setYCor("126.874431");
        StadiumEntity18.setStdAddress("서울특별시 풋살구장 19번");
        StadiumEntity18.setStdStart("10:00");
        StadiumEntity18.setStdEnd("22:00");
        StadiumEntity18.setStdPn("010-1234-5678");
        StadiumEntity18.setStdImgUrl("https://www.google.com/images?q=cat");
        StadiumEntity18.setStdMng("???");
        StadiumEntity18.setStdDetails("풋살장에 대한 상세 정보 19");

        StadiumEntity StadiumEntity19 = new StadiumEntity();
        StadiumEntity19.setMngId("???");
        StadiumEntity19.setStdName("풋살장 20");
        StadiumEntity19.setStdLink("http://www.example.com/20");
        StadiumEntity19.setXCor("37.675512");
        StadiumEntity19.setYCor("126.884979");
        StadiumEntity19.setStdAddress("서울특별시 풋살구장 20번");
        StadiumEntity19.setStdStart("10:00");
        StadiumEntity19.setStdEnd("22:00");
        StadiumEntity19.setStdPn("010-9876-5432");
        StadiumEntity19.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity19.setStdMng("???");
        StadiumEntity19.setStdDetails("풋살장에 대한 상세 정보 20");

        StadiumEntity StadiumEntity20 = new StadiumEntity();
        StadiumEntity20.setMngId("???");
        StadiumEntity20.setStdName("풋살장 21");
        StadiumEntity20.setStdLink("http://www.example.com/21");
        StadiumEntity20.setXCor("37.695510");
        StadiumEntity20.setYCor("126.892253");
        StadiumEntity20.setStdAddress("서울특별시 풋살구장 21번");
        StadiumEntity20.setStdStart("10:00");
        StadiumEntity20.setStdEnd("22:00");
        StadiumEntity20.setStdPn("010-5555-5555");
        StadiumEntity20.setStdImgUrl("https://www.naver.com/images?q=dog");
        StadiumEntity20.setStdMng("???");
        StadiumEntity20.setStdDetails("풋살장에 대한 상세 정보 21");

        List<StadiumEntity> stadiums = new ArrayList<>();
        stadiums.add(StadiumEntity1);
        stadiums.add(StadiumEntity2);
        stadiums.add(StadiumEntity3);
        stadiums.add(StadiumEntity4);
        stadiums.add(StadiumEntity5);
        stadiums.add(StadiumEntity6);
        stadiums.add(StadiumEntity7);
        stadiums.add(StadiumEntity8);
        stadiums.add(StadiumEntity9);
        stadiums.add(StadiumEntity10);
        stadiums.add(StadiumEntity11);
        stadiums.add(StadiumEntity12);
        stadiums.add(StadiumEntity13);
        stadiums.add(StadiumEntity14);
        stadiums.add(StadiumEntity15);
        stadiums.add(StadiumEntity16);
        stadiums.add(StadiumEntity17);
        stadiums.add(StadiumEntity18);
        stadiums.add(StadiumEntity19);
        stadiums.add(StadiumEntity20);


        // Repository를 통해 데이터 저장
        stadiumRepository.saveAll(stadiums);
    }

}
