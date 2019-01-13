package com.ab.auth;


import com.ab.auth.entity.UserInfo;
import com.ab.auth.utils.JwtUtils;
import com.ab.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/*
 *  @项目名：  leiyou 
 *  @包名：    com.leyou.auth
 *  @文件名:   JwtTest
 *  @创建者:   Unow
 *  @创建时间:  2018/12/26 0:49
 *  @描述：    TODO
 */
public class JwtTest {
    private static final String pubKeyPath = "rsa.pub";

    private static final String priKeyPath = "rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(123L, "123"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJOYW1lIjoiamFjayIsImV4cCI6MTUyNzMzMDY5NX0.VpGNedy1z0aR262uAp2sM6xB4ljuxa4fzqyyBpZcGTBNLodIfuCNZkOjdlqf-km6TQPoz3epYf8cc_Rf9snsGdz4YPIwpm6X14JKU9jwL74q6zy61J8Nl9q7Zu3YnLibAvcnC_y9omiqKN8-iCi7-MvM-LwVS7y_Cx9eu0aaY8E";
        String token2 = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU0NTc1NzgxNX0.dRNKxCzu17-toiBLXaiGLt9_dJRS2OTStOD9K24DZs5O7dCcxRJAvRTRRf5agTjk7FMgvmpenSHg_GxW8_pLtyLUlIiUxu9-cJ_fDhflfjtLQmyi6vGCCtXzJWZ2_zKYpPrK2AG37FufcFM73vXLHb0A934dYEBUcsSDTKEDfts";
        String token3 = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MTIzLCJ1c2VybmFtZSI6IjEyMyIsImV4cCI6MTU0NjQxNzU2MH0.F1Q6pf8loHLgek4I8LO02DrHldzwcAPw_ZWo1OR-AfMDvgwoIbug7mRN3_jDeEWGIPJjs8OoJssBVmh4qb9QlJaN800VBUxWlK0hUCs7kFf2oVvJjQ5QZ2ol643yhd6lfi6L4iwiL28e4stUFpFc63jx3y47uF3pZJvZV1GRLD0";
        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token3, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
