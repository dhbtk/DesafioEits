package com.stockontrol.test.domain;

import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration({ "/jpaContext.xml", "/testContext.xml", "/security.xml" })
@WithUserDetails("admin@teste.com")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, FlywayTestExecutionListener.class, WithSecurityContextTestExecutionListener.class })
public abstract class AbstractIntegrationTests
{

}
