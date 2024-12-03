import { WebPlugin } from '@capacitor/core';

import type { AppodealPluginPlugin } from './definitions';

export class AppodealPluginWeb extends WebPlugin implements AppodealPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
