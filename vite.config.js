import { resolve } from 'path'
import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";

export default defineConfig({
  publicDir: './src/main/static/public',
  plugins: [scalaJSPlugin()],
  resolve: {
    alias: {
      'stylesheets': resolve(__dirname, './src/main/static/stylesheets'),
    }
  }
});
