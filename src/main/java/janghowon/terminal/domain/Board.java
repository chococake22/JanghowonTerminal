package janghowon.terminal.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PUBLIC)  // 기본 생성자의 접근을 protected로 설정
@Getter
@Entity
@Table(name = "Board")
public class Board extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 DB에 위임함
    @Column(name = "board_id")
    private Long id;

    // EAGER는 실무에서는 권장하지 않는 방법이다.
    // 일단은 account의 username을 가져오기 위해서 EAGER로 설정
    // 지금 프로젝트의 경우 board를 가져오는 경우가 게시글 쓰기, 수정, 삭제에서만 가져오기 때문에 일단 EAGER로 했다.
    // 추후에 페치 조인을 통해 수정할 예정이다.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 일대다 양방향 매핑 (mappedby = 상대방 테이블의 어느 필드를 참조하는지 - 주 객체의 주인을 설정한다.)
    // CascadeType.ALL -> 영속성 전이를 통해 board 삭제시 comments도 삭제가 되도록 한다.
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(Long id, Account account, String title, String content, List<Comment> comments) {
        this.id = id;
        this.account = account;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }
}
