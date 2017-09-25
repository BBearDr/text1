package org.springframework.samples.mvc.data.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
/**
 * ע�����õ�Ŀ��:
 * @Target(ElementType.TYPE)   //�ӿڡ��ࡢö�١�ע��
*��@Target(ElementType.FIELD) //�ֶΡ�ö�ٵĳ���
*��@Target(ElementType.METHOD) //����
*��@Target(ElementType.PARAMETER) //��������
*��@Target(ElementType.CONSTRUCTOR)  //���캯��
 *��@Target(ElementType.LOCAL_VARIABLE) //�ֲ�����
 *��@Target(ElementType.ANNOTATION_TYPE) //ע��
 *��@Target(ElementType.PACKAGE) ///��
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 *ע��ı���λ��
 * @Retention(RetentionPolicy.SOURCE) ע���������Դ����,��class�ֽ����ļ��в�����
 * @Retention(RetentionPolicy.CLASS) Ĭ�ϵı�������,ֻ�Ǵ���class�ֽ����ļ���,����������ʱ�޷����
 * @Retention(RetentionPolicy.RUNTIME) ע�����class�ֽ����ļ���,����ʱ����ͨ������õ�
 */
@Documented
/**
 * ��ע��ᱻ������javadoc�ĵ���
 */
public @interface RequestAttribute {
	String value();
}