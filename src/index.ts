import { registerPlugin } from '@capacitor/core';

import type { AppodealPluginPlugin } from './definitions';

const AppodealPlugin = registerPlugin<AppodealPluginPlugin>('AppodealPlugin', {
  web: () => import('./web').then((m) => new m.AppodealPluginWeb()),
});

export * from './definitions';
export { AppodealPlugin };
