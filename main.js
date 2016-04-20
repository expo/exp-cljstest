'use strict';

const figwheel = true;

if (figwheel) {
  require('figwheel-bridge').withModules({
    'react-native': require('react-native'),
    'react': require('react'),
  }).start('main');
} else {
  require('./index');
}
