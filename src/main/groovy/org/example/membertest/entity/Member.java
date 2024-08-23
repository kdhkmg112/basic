package org.example.membertest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id // 이 필드가 엔티티의 기본 키임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키값의 생성 방식을 설정 (Auto-increment)
    private Long id;

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
