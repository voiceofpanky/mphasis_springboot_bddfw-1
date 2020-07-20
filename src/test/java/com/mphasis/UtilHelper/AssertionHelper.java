package com.mphasis.UtilHelper;
import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Vikas Swami
 *
 */
@Slf4j
public class AssertionHelper {

	public static void verifyText(String s1, String s2) {
		log.info("veryfing test: " + s1 + " with " + s2);
		Assert.assertEquals(s1, s1);
	}

	public static void markPass() {
		log.info("making script PASS..");
		Assert.assertTrue(true);
	}

	public static void markPass(String message) {
		log.info("making script PASS.." + message);
		Assert.assertTrue(message, true);
	}

	public static void markFail() {
		log.info("making script FAIL..");
		Assert.assertTrue(false);
	}

	public static void markFail(String message) {
		log.info("making script FAIL.." + message);
		Assert.assertTrue(message, false);
		
		
	}

	public static void verifyTrue(boolean status) {
		Assert.assertTrue(status);
	}

	public static void verifyFalse(boolean status) {
		Assert.assertFalse(status);
	}

	public static void verifyNull(String s1) {
		log.info("verify object is null..");
		Assert.assertNull(s1);
	}

	public static void verifyNotNull(String s1) {
		log.info("verify object is not null..");
		Assert.assertNotNull(s1);
	}

	public static void fail() {
		Assert.assertTrue(false);
	}

	public static void pass() {
		Assert.assertTrue(true);
	}

	public static void updateTestStatus(boolean status) {
		if (status) {
			pass();
		} else {
			fail();
		}
	}
}
