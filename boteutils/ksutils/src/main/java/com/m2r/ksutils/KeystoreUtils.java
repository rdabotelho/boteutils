package com.m2r.ksutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.Principal;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.m2r.ksutils.utils.SubjectDN;
import com.m2r.ksutils.validate.CertificateVerifier;

public class KeystoreUtils {

	private static final String KS_PASS = "password";

	public static void validateCertificate(KeyStore ks, boolean validateCRL) throws Exception {
		X509Certificate cert = getSingleCertificate(ks);
		Certificate[] intermediates = ks.getCertificateChain(ks.getCertificateAlias(cert));
		Set<X509Certificate> intermediatesSet = new HashSet<X509Certificate>();
		for (Certificate c : intermediates) {
			intermediatesSet.add((X509Certificate) c);
		}
		Security.addProvider(new BouncyCastleProvider());
		CertificateVerifier.verifyCertificate(cert, intermediatesSet, validateCRL);
	}

	private static File getKeyStoreFile() {

		File dir = new File(System.getProperty("user.home"), ".ssh");
		if (!dir.exists()) {			dir.mkdir();
		}
		return new File(dir, "nfsd.jks");
	}

	public static void createKeystore() throws Exception {

		File file = getKeyStoreFile();
		if (file.exists()) {
			return;
		}

		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

		char[] password = KS_PASS.toCharArray();
		ks.load(null, password);

		FileOutputStream fos = new FileOutputStream(file);
		ks.store(fos, password);
		fos.close();
	}

	public static void createDefaultKeyStore() throws Exception {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] password = KS_PASS.toCharArray();
		ks.load(null, password);
		saveDefaultKeyStore(ks);
	}

	public static void saveDefaultKeyStore(KeyStore ks) throws Exception {
		FileOutputStream fos = new FileOutputStream(getKeyStoreFile());
		char[] password = KS_PASS.toCharArray();
		ks.store(fos, password);
		fos.close();
	}

	public static KeyStore loadDefaultKeyStore() throws Exception {
		File file = getKeyStoreFile();
		if (!file.exists()) {
			createDefaultKeyStore();
		}
		return loadKeyStore(getKeyStoreFile(), KS_PASS);
	}

	public static KeyStore loadKeyStore(File file, String password) throws Exception {
		if (file.getName().toUpperCase().endsWith(".PFX")) {
			return loadKeyStore(file, password, "pkcs12");
		}
		return loadKeyStore(file, password, KeyStore.getDefaultType());
	}

	public static KeyStore loadKeyStore(File file, String password, String type) throws Exception {
		KeyStore ks = KeyStore.getInstance(type);
		ks.load(new FileInputStream(file), password.toCharArray());
		return ks;
	}

	public static X509Certificate importCertificateAndValidate(File file, String password, boolean validateCRL) throws Exception {
		KeyStore p12 = loadKeyStore(file, password);
		validateCertificate(p12, validateCRL);
		return importCertificate(p12);
	}

	public static X509Certificate importCertificate(File file, String password) throws Exception {
		KeyStore p12 = loadKeyStore(file, password);
		return importCertificate(p12);
	}

	private static X509Certificate importCertificate(KeyStore p12) throws Exception {
		X509Certificate cert = getSingleCertificate(p12);
		KeyStore ks = loadDefaultKeyStore();
		ks.setCertificateEntry(p12.getCertificateAlias(cert), cert);
		saveDefaultKeyStore(ks);
		return cert;
	}

	public static void deleteCertificate(X509Certificate certificate) throws Exception {
		KeyStore ks = loadDefaultKeyStore();
		String alias = ks.getCertificateAlias(certificate);
		ks.deleteEntry(alias);
		saveDefaultKeyStore(ks);
	}

	public static Set<X509Certificate> getCertificates(KeyStore ks) throws Exception {
		Set<X509Certificate> list = new HashSet<X509Certificate>();
		Enumeration<?> e = ks.aliases();
		while (e.hasMoreElements()) {
			String alias = (String) e.nextElement();
			X509Certificate c = (X509Certificate) ks.getCertificate(alias);
			list.add(c);
		}
		return list;
	}

	public static X509Certificate getCertificate(KeyStore ks, String alias) throws Exception {
		return (X509Certificate) ks.getCertificate(alias);
	}

	public static X509Certificate getSingleCertificate(KeyStore ks) throws Exception {
		Enumeration<?> e = ks.aliases();
		while (e.hasMoreElements()) {
			String alias = (String) e.nextElement();
			return (X509Certificate) ks.getCertificate(alias);
		}
		return null;
	}

	public static void showKeystore() throws Exception {
		KeyStore ks = loadDefaultKeyStore();
		showKeystore(ks);
	}

	public static void showKeystore(KeyStore ks) throws Exception {
		Enumeration<?> e = ks.aliases();
		while (e.hasMoreElements()) {
			String alias = (String) e.nextElement();
			X509Certificate c = (X509Certificate) ks.getCertificate(alias);
			showCertificate(c);
		}
	}

	public static void showCertificate(X509Certificate c) {
		Principal subject = c.getSubjectDN();
		String subjectArray[] = subject.toString().split(",");
		for (String s : subjectArray) {
			String[] str = s.trim().split("=");
			String key = str[0];
			String value = str[1];
			System.out.println(key + " - " + value);
		}
	}

	public static SubjectDN getSubjectDN(Principal subject) {
		return SubjectDN.getInstance(subject);
	}

	public static String encodeToBase64String(Certificate cert) throws Exception {
		return Base64.getEncoder().encodeToString(cert.getEncoded());
	}

}
