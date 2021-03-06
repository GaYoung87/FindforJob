package com.back.admin.domain.user;

import com.back.admin.domain.experience.Experience;
import com.back.admin.domain.mentor.Mentor;
import com.back.admin.domain.sol_question.SolQuestion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_no;

    @Column
    private int user_auth;  //1: 학생일반 2: 학생우수 3: 관리자 4: 인증 대기 멘토 5: 멘토 일반 6: 멘토 우수

    @Column
    private String user_name;

    @Column
    private String user_school;

    @Column
    private String user_major;

    @Column
    private String user_id_email;  // email = id

    @Column
    private String user_password;

    @Column
    private Long user_total_mileage;

    // fk -> 1:N = student:experience
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "studentexperience")
    @JsonManagedReference
    private List<Experience> experience=new ArrayList<>();

    // 1:N = student:sol_q
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "studentsolq")
    @JsonManagedReference
    private List<SolQuestion> solquestion=new ArrayList<>();

    // 1:N = student:mentor
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "mentor")
    @JsonManagedReference
    private List<Mentor> mentor=new ArrayList<>();

    @Builder
    public User(int user_auth, String user_name, String user_school, String user_major,
                   String user_id_email, String user_password, Long user_total_mileage) {
        this.user_auth = user_auth;
        this.user_name = user_name;
        this.user_school = user_school;
        this.user_major = user_major;
        this.user_id_email = user_id_email;
        this.user_password = user_password;
        this.user_total_mileage = user_total_mileage;
    }

    public void update(String user_school, String user_major, String user_password) {
        this.user_school = user_school;
        this.user_major = user_major;
        this.user_password = user_password;
    }
}
