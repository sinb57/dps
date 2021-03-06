package com.project.dps.domain.scenario.stage.poc;

import com.project.dps.domain.scenario.stage.Stage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_common")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestCommon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_common_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "testCommon")
    private List<TestCase> testCaseList = new ArrayList<>();

    @Column(length = 2000)
    private String content; // POC 공통 분모


    //== Builder 메서드 ==//
    @Builder
    public TestCommon(Stage stage, List<TestCase> testCaseList, String content) {
        this.testCaseList = testCaseList;
        this.content = content;

        // 연관관계 로직
        this.stage = stage;
        stage.setTestCommon(this);
    }


    //== 비즈니스 로직 ==//
    public void appendTestCaseList(TestCase testCase) {
        this.testCaseList.add(testCase);
    }

    public void removeTestCaseList(TestCase testCase) {
        this.testCaseList.remove(testCase);
    }

    public void edit(String content) {
         this.content = content;
    }
}
