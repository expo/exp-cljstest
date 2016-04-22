'use strict';

const figwheel = true;

if (figwheel) {
  require('figwheel-bridge').withModules({
    'dev-env': require('../target/dev-env.js'),

    'react-native': require('react-native'),
    'react': require('react'),
  }).start('main');
} else {
  require('../target/index.js');
}
