package io.sl.ex.junit5.suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import io.sl.ex.junit5.order.TestOrderService;
import io.sl.ex.junit5.payment.TestPaymentService;
import io.sl.ex.junit5.user.TestUserService;

@RunWith(JUnitPlatform.class)
@SelectClasses({TestUserService.class, TestOrderService.class, TestPaymentService.class})
public class PlayOrderFeatureSuite {
}
