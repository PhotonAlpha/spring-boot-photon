import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class AppConfigs {
    public static readonly token_key = 'ethon.1';
    public static readonly refresh_token_key = 'ethon.1.r';

    public static readonly API_BASE = `${environment.apiUrl}/v1`;
    public static readonly AUTH_URL = `${environment.apiUrl}/api/auth`;
    public static readonly REFRESH_URL = `${environment.apiUrl}/api/refresh`;

    public static readonly login_user = `${AppConfigs.API_BASE}/user`;
}
