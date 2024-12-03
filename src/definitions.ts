export interface AppodealPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
