propagation ����Ĵ�����һ����7�֣���������
REQUIRED
SUPPORTS
MANDATORY
REQUIRES_NEW
NOT_SUPPORTED
NEVER
NESTED

@Transactional(propagation=Propagation.REQUIRED)        //����ǰ����ִ��������ֳɵ���������ֳɵģ����Ὺ���������Ĭ�ϵ�
@Transactional(propagation=Propagation.REQUIRES_NEW)    //����ǰ����ִ�п���������,���Ը������ʧ�ܶ��ϼ����ﲻ�ṹ��Ӱ��

isolation  ����ĸ�����
DEFAULT
READ_UNCOMMITTED  ��ȡδ�ύ
READ_COMMITTED    ��ȡ���ύ ����
REPEATABLE_READ
SERIALIZABLE
@Transactional(isolation=Isolation.READ_COMMITTED)

readOnly ֻ�����ԣ�һ��find������������ΪreadOnly=true���Լӿ����ݿ����
@Transactional(readOnly=true)

timeout ��ʱ�ع�����λ��
@Transactional(timeout=1)  //1�������δ��ɾͻع�

���յ��쳣�ز��ع�
@Transactional(
	rollbackFor={UserAccountException.class},
	rollbackForClassName={"xxx.xxx.xxException"},
	noRollbackFor={UserAccountException.class},
	noRollbackForClassName={"xxx.xxx.xxException"})