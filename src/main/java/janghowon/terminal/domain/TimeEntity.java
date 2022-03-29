package janghowon.terminal.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // 따로 테이블이 생성되는 것은 아니다 -> 그냥 자식 클래스(엔티티)에 매핑정보를 상속한다. 트랜잭션 커밋 시점에 hibernate가 자동으로 값을 채워준다.
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {  // 추상 클래스로 생성한다.

    // 생성 날짜
    // 수정은 불가능
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    // 마지막 수정 날짜
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
