package com.simple.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.Objects;

/**
 * MyBatis 会话获取工具类
 * <p>
 * 双重检查模式，保证单例
 *
 * @author Jinhua
 * @version 1.1   增加单例控制
 * @date 2020/12/26 1:42
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyBatisSessionUtil {

    /**
     * 会话工厂
     */
    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    /**
     * 单例对象
     */
    private static volatile MyBatisSessionUtil singleSession;

    static {
        final String resource = "SqlMapConfig.xml";
        // 创建 SqlSession 工厂
        SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder()
                .build(MyBatisSessionUtil.class.getClassLoader().getResourceAsStream(resource));
    }

    public static MyBatisSessionUtil getInstance() {
        if (Objects.isNull(singleSession)) {
            synchronized (MyBatisSessionUtil.class) {
                if (Objects.isNull(singleSession)) {
                    singleSession = new MyBatisSessionUtil();
                }
            }
        }
        return singleSession;
    }

    /**
     * 从工厂获取会话对象
     *
     * @param autoCommit 会话是否开启自动提交
     * @return 会话对象
     */
    public SqlSession getSession(boolean autoCommit) {
        return SQL_SESSION_FACTORY.openSession(autoCommit);
    }

}
