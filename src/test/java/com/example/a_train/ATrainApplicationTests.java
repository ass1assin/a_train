package com.example.a_train;


import com.example.a_train.mapper.MainMapper;
import com.example.a_train.service.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class ATrainApplicationTests {

    @Autowired
    private MainMapper mainMapper;

    @Autowired
    private MainService mainService;




//    @Test
//    void testSave() {
//        // 准备一个待测试的 Main 对象
//        Main mainToSave = new Main();
//        mainToSave.setId(Math.toIntExact(1L));
//        mainToSave.setName("测试主对象");
//
//        // 模拟 mainMapper.insert 方法的行为
//        when(mainMapper.insert(any(Main.class))).thenReturn(2); // 假设插入成功影响了 1 行
//
//        // 调用保存方法
//        boolean result = mainService.save(mainToSave);
//
//        // 验证 mainMapper.insert 方法是否被正确调用，并传递了正确的 Main 对象
//        verify(mainMapper).insert(mainToSave);
//
//        // 验证保存操作的结果是否为 true（根据上述模拟行为）
//        assertTrue(result);
//    }
    @Test
    void testSave(){}
    @Test
    void testGetAll(){
        System.out.println(mainMapper.selectList(null));
    }
}
