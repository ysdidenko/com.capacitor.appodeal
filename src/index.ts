import { registerPlugin } from '@capacitor/core';

import type { AppodealPlugin } from './definitions';

const Appodeal = registerPlugin<AppodealPlugin>('AppodealPlugin');

export * from './definitions';
export { Appodeal };
