package cn.plusman.springtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author plusman
 * @since 2021/4/11 11:19 AM
 */
@Component
public class InnerCallService {
    @Autowired
    private InnerCallService innerCallService;
    
    @Transactional
    public void invoice() {
        // send invoice as email, etc.
        createPdf();
        
        // 自注入方式
        innerCallService.createPdf();
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createPdf() {
        // ...
    }
}
