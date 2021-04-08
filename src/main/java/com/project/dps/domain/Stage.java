package com.project.dps.domain;

import com.project.dps.domain.log.ScenarioPassLog;
import com.project.dps.domain.log.StagePassLog;
import com.project.dps.domain.poc.Poc;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Stage {

    @Id @GeneratedValue
    @Column(name = "stage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @OneToMany(mappedBy = "stage")
    private List<Poc> pocList = new ArrayList<>();

    @OneToMany(mappedBy = "stage")
    private List<StagePassLog> logList = new ArrayList<>();

    private Long no; // 스테이지 번호
    private String content; // 스테이지 내용


    // 연관관계 메서드
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
        scenario.getStageList().add(this);
    }

}
