export enum AppodealAdType {
  INTERSTITIAL = 3,
  BANNER = 4,
  BANNER_BOTTOM = 8,
  BANNER_TOP = 16,
  REWARDED_VIDEO = 128,
  NON_SKIPPABLE_VIDEO = 256,
}

export interface AppodealPlugin {
  initialize(options: { appKey: string; adType: number }): Promise<void>;
  muteVideosIfCallsMuted(options: { value: boolean }): Promise<void>;
  setInterstitialCallbacks(): Promise<void>;
  show(options: { adType: number }): Promise<{ result: boolean }>;
  cache(options: { adType: number }): Promise<void>;
  isLoaded(options: { adType: number }): Promise<{ isLoaded: boolean }>;

  addListener(eventName: 'onInterstitialEvent', listenerFunc: (info: any) => void): Promise<any>;
}
