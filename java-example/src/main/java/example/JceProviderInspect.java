package example;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Set;

public class JceProviderInspect {

	public static void main(String[] args) {

		// 登録されているJCEプロバイダ一覧を取得
		Provider[] providers = Security.getProviders();

		// プロバイダ毎で提供される全てのサービスを出力
		for (Provider provider : providers) {
			showServices(null, provider);
			// showServices(MessageDigest.class.getSimpleName(), provider);
		}

	}

	/**
	 * プロバイダに含まれる各サービスの情報を出力する。
	 * 
	 * @param type     タイプ
	 * @param provider JCEプロバイダ
	 */
	public static void showServices(String type, Provider provider) {
		Set<Service> serviceSet = provider.getServices();
		for (Service service : serviceSet) {
			if (type == null || service.getType().equals(type)) {
				System.out.print(provider.getName());
				System.out.print("," + service.getType());
				System.out.print("," + service.getAlgorithm());
				System.out.println();
			}
		}
	}

}
