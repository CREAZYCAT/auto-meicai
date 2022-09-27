package com.github.automeican;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.automeican.api.IndexRestApi;
import com.github.automeican.dao.entity.MeicanAccount;
import com.github.automeican.dao.entity.MeicanBooking;
import com.github.automeican.dao.service.IMeicanAccountService;
import com.github.automeican.dao.service.IMeicanBookingService;
import com.github.automeican.dao.service.IMeicanDishService;
import com.github.automeican.remote.AuthService;
import com.github.automeican.remote.MeicanClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class AutoMeicaiApplicationTests {

    @Resource
    private AuthService authService;
    @Resource
    private MeicanClient meicanClient;
    @Resource
    private IMeicanAccountService meicanAccountService;
    @Resource
    private IMeicanBookingService meicanBookingService;
    @Resource
    private IndexRestApi indexRestApi;

    @Test
    void test() {
        System.out.println(indexRestApi.dishList(""));
        System.out.println(indexRestApi.dishList("123"));
        System.out.println(indexRestApi.dishList(null));
    }

////        param.setUsername("18762036036@shinho.net.cn");
////        param.setPassword("Shinho123");
////        param.setUsername("13918961790@shinho.net.cn");
////        param.setPassword("Shinho123");
//        param.setMeicanAccountName("13764723439@shinho.net.cn");
//        param.setMeicanAccountPassword("123456");
    @Test
    void test1() {

        MeicanAccount account = new MeicanAccount();
        account.setAccountName("18762036036@shinho.net.cn");
        account.setAccountPassword("Shinho123");
//        meicanAccountService.remove(Wrappers.<MeicanAccount>lambdaQuery().eq(MeicanAccount::getAccountName,account.getAccountName()));
        meicanAccountService.save(account);
        MeicanBooking booking = new MeicanBooking();
        booking.setAccountName("18762036036@shinho.net.cn");
        booking.setOrderDate(LocalDate.now().toString());
        booking.setOrderDish("鲍鱼");
        meicanClient.executeTask(booking);
    }


}
